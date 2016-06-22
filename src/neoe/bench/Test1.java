package neoe.bench;

import java.io.File;
import java.io.RandomAccessFile;

public class Test1 {
	static final int SIZE = 1000000;
	static final int SIZE2 = SIZE / 10;
	// mem size
	static final int refvalue1 = 38464;
	// disk size/10
	static final int refvalue2 = 70176;

	public static class MyThread extends Thread {
		protected int x;

		MyThread(int i) {
			this.x = i;
		}
	}

	static class CpuMem1 implements BenchTask {
		int cnt = 0;

		@Override
		public double run(int index) throws Exception {
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

			if (p != refvalue1) {
				System.out.printf("bad refvalue=%d should be %d\n", p, refvalue1);
			}
			return size * 3;
		}

	}

	static class Disk1 implements BenchTask {

		@Override
		public double run(int index) throws Exception {
			int size = SIZE2;
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

				if (p != refvalue2) {
					System.out.printf("bad refvalue=%d should be %d\n", p, refvalue2);
				}
			}
			new File(fn).delete();
			return size * 3;
		}

		int cnt;
	}

	static final int MT = 16;// threads
	static final int TEST_TIME_MS = 6000;

	public static void main(String[] args) {
		System.out.println("Start benchmark v2.2");

		try {
			bench(TEST_TIME_MS, new CpuMem1(), "CpuMemSingle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			multiThreadBench(MT, new BenchTask() {
				@Override
				public double run(int index) throws Exception {
					return bench(TEST_TIME_MS, new CpuMem1(), String.format("CpuMemMT%02d", index));
				}
			}, "CpuMemMT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bench(TEST_TIME_MS, new Disk1(), "DiskSingle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static double multiThreadBench(int cc, final BenchTask benchTask, String myname) {
		long t1 = System.currentTimeMillis();
		Thread[] th = new Thread[cc];
		final double[] v = new double[cc];
		for (int i = 0; i < cc; i++) {
			final int index = i;
			th[i] = new MyThread(i) {
				public void run() {
					try {
						v[x] = benchTask.run(index);
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
		double score = sum / (double) t;
		System.out.printf("%s x%d in %,d ms, score = %,d \n", myname, cc, t, (long) score);
		return score;
	}

	private static double bench(long time, BenchTask task, String name) throws Exception {
		// System.out.println("Start benchmark:" + name);
		long t1 = System.currentTimeMillis();
		long performance = 0;
		long t = 1;
		int turn = 0;
		while (true) {
			turn++;
			performance += task.run(turn);
			long t2 = System.currentTimeMillis();
			t = t2 - t1;
			if (t >= time) {
				break;
			}
		}
		double score = performance / (double) t;
		System.out.printf("%s in %,d ms(%,d turns), score = %,d \n", name, t, turn, (long) score);
		return performance;
	}

	interface BenchTask {
		double run(int index) throws Exception;
	}
}
