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
			
			if (p !=refvalue1) {
				System.out.printf("bad refvalue=%d should be %d\n" , p, refvalue1);
			}
			return size * 3;
		}

	}
	static int refvalue1 =  38464;
	static int refvalue2 =  70176;

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
				
				if (p !=refvalue2) {
					System.out.printf("bad refvalue=%d should be %d\n" , p, refvalue2);
				}
			}
			new File(fn).delete();
			return size * 3;
		}

		int cnt;
	}
	static final int MT = 16;//threads
	public static void main(String[] args) {
		try {
			multiThreadBench(MT, new BenchTask() {
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
		double ret = sum / (double) t;
		System.out.printf("multiThreadBench x%,d finished in %,d ms, score = %,d \n"
			, cc, t, (long) ret);
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
		System.out.printf("benchmark:%s finished in %,d ms, %,d turns, score = %,d \n" 
			, name, t, turn, (long) score);
		return performance;
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

E5408 4 core, sata2

benchmark:CpuMem finished in 10,044 ms, 78 turns, score = 23,297
benchmark:CpuMem finished in 10,023 ms, 81 turns, score = 24,244
benchmark:CpuMem finished in 10,059 ms, 84 turns, score = 25,052
benchmark:CpuMem finished in 10,020 ms, 83 turns, score = 24,850
benchmark:CpuMem finished in 10,064 ms, 63 turns, score = 18,779
benchmark:CpuMem finished in 10,028 ms, 76 turns, score = 22,736
benchmark:CpuMem finished in 10,067 ms, 79 turns, score = 23,542
benchmark:CpuMem finished in 10,091 ms, 68 turns, score = 20,216
benchmark:CpuMem finished in 10,079 ms, 80 turns, score = 23,811
benchmark:CpuMem finished in 10,075 ms, 71 turns, score = 21,141
benchmark:CpuMem finished in 10,012 ms, 92 turns, score = 27,566
benchmark:CpuMem finished in 10,015 ms, 85 turns, score = 25,461
benchmark:CpuMem finished in 10,013 ms, 114 turns, score = 34,155
benchmark:CpuMem finished in 10,015 ms, 123 turns, score = 36,844
benchmark:CpuMem finished in 10,008 ms, 122 turns, score = 36,570
benchmark:CpuMem finished in 10,019 ms, 137 turns, score = 41,022
multiThreadBench x16 finished in 12,406 ms, score = 347,251
Start benchmark:Disk
benchmark:Disk finished in 13,559 ms, 2 turns, score = 44
			
 */
