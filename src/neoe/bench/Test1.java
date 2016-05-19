package neoe.bench;

import java.io.File;
import java.io.RandomAccessFile;

public class Test1 {
	static final int SIZE = 1000000;

	public static class MyThread extends Thread {
		protected int x;

		MyThread(int i) {
			this.x = i;
		}
	}

	static class CpuMem1 implements BenchTask {
		int cnt = 0;

		@Override
		public double run() throws Exception {
			int size = SIZE;
			int[] bs = new int[size];
			for (int i = 0; i < size; i++) {
				bs[i] = i + 1;
			}
			for (int i = 0; i < size; i++) {
				bs[i] = bs[i] * (i + 1);
			}
			int p = 0;
			for (int i = 0; i < size; i++) {
				p += bs[p] + 1;
				p = p * 7;
				while (p < 0)
					p += size;
				p = p % size;
			}
			if (cnt < 3) {
				System.out.println("refvalue=" + p);
				cnt++;
			}
			return size * 3;
		}

	}

	static class Disk1 implements BenchTask {

		@Override
		public double run() throws Exception {
			int size = SIZE / 10;
			String fn = "bm.tmp." + Long.toString(System.currentTimeMillis(), 36);
			{
				RandomAccessFile raf = new RandomAccessFile(fn, "rw");
				for (int i = 0; i < size; i++) {
					raf.writeInt(i + 1);
				}
				raf.close();
			}
			{
				RandomAccessFile raf = new RandomAccessFile(fn, "rw");
				for (int i = 0; i < size; i++) {
					int v = raf.readInt();
					v = v * (i + 1);
					raf.seek(i * 4);
					raf.writeInt(v);
				}
				raf.close();
			}
			{
				RandomAccessFile raf = new RandomAccessFile(fn, "rw");
				int p = 0;
				for (int i = 0; i < size; i++) {
					raf.seek(p * 4);
					int v = raf.readInt();
					p += v + 1;
					p = p * 7;
					while (p < 0)
						p += size;
					p = p % size;
				}
				raf.close();
				if (cnt < 3) {
					System.out.println("disk refvalue=" + p);
					cnt++;
				}
			}
			new File(fn).delete();
			return size * 3;
		}

		int cnt;
	}

	public static void main(String[] args) {
		try {
			multiThreadBench(4, new BenchTask() {
				@Override
				public double run() throws Exception {
					return bench(10000, new CpuMem1(), "CpuMem");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bench(10000, new Disk1(), "Disk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static double multiThreadBench(int cc, final BenchTask benchTask) {
		long t1 = System.currentTimeMillis();
		Thread[] th = new Thread[cc];
		final double[] v = new double[cc];
		for (int i = 0; i < cc; i++) {
			th[i] = new MyThread(i) {
				public void run() {
					try {
						v[x] = benchTask.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			th[i].start();
		}

		double sum = 0;
		for (int i = 0; i < cc; i++) {
			try {
				th[i].join();
				sum += v[i];
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long t2 = System.currentTimeMillis();
		long t = t2 - t1;
		if (t == 0)
			t = 1;
		double ret = sum / (double) cc;
		System.out.println("multiThreadBench x" + cc + " finished in " + t + ", score = " + ret);
		return ret;
	}

	private static double bench(long time, BenchTask task, String name) throws Exception {
		System.out.println("Start benchmark:" + name);
		long t1 = System.currentTimeMillis();
		long performance = 0;
		long t = 1;
		int turn = 0;
		while (true) {
			turn++;
			performance += task.run();
			long t2 = System.currentTimeMillis();
			t = t2 - t1;
			if (t >= time) {
				break;
			}
		}
		double score = performance / (double) t;
		System.out.println("benchmark:" + name + " finished in " + t + ", turn = " + turn + " score = " + score);
		return score;
	}

	interface BenchTask {
		double run() throws Exception;
	}
}
/*-
 * E2180 dual core 2G Pentium ============
 * refvalue=38464
 * multiThreadBench x4 finished in 10197, score = 28629.44906124538
benchmark:CpuMem finished in 10030, turn = 93 score = 27816.55034895314
benchmark:CpuMem finished in 10044, turn = 93 score = 27777.777777777777
benchmark:CpuMem finished in 10019, turn = 137 score = 41022.0580896297
benchmark:CpuMem finished in 10004, turn = 139 score = 41683.326669332266
Start benchmark:Disk
disk refvalue=70176
disk refvalue=70176
benchmark:Disk finished in 15311, score = 39.187512246097576
==========================
 */