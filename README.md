# simplebench
neoe simple benchmark program on CPU,memory and disk. Quick and meaningful results.

## usage
run in commmand line(need recent JRE or JDK):
```
> git clone https://github.com/neoedmund/simplebench
> cd simplebench
> java -jar dist/simplebench.jar
```

## some results

### E2180 dual core 2G Pentium (FIXME:old data)　
```
> refvalue=38464
> multiThreadBench x4 finished in 10197, score = 28629.44906124538
> benchmark:CpuMem finished in 10030, turn = 93 score = 27816.55034895314
> benchmark:CpuMem finished in 10044, turn = 93 score = 27777.777777777777
> benchmark:CpuMem finished in 10019, turn = 137 score = 41022.0580896297
> benchmark:CpuMem finished in 10004, turn = 139 score = 41683.326669332266
> Start benchmark:Disk
> disk refvalue=70176
> disk refvalue=70176
> benchmark:Disk finished in 15311, score = 39.187512246097576
```



### OrangePi PC (H3)  (FIXME:old data)　
```
> benchmark:CpuMem finished in 12254, turn = 4 score = 979.2720744246776
> benchmark:CpuMem finished in 12279, turn = 4 score = 977.2782799902272
> benchmark:CpuMem finished in 12310, turn = 4 score = 974.817221770918
> benchmark:CpuMem finished in 12325, turn = 4 score = 973.630831643002
> multiThreadBench x4 finished in 12337, score = 976.2496019572062
> Start benchmark:Disk
> disk refvalue=70176
> benchmark:Disk finished in 13301, turn = 1 score = 22.554695135704083
```

### banabapi (arm)  (FIXME:old data)　
```
> multiThreadBench x4 finished in 16419, score = 367.20373048067046
```

### AMD FX8320 8 cores
```
Start benchmark v3.1
CpuMemSingle in 6,005 ms(274 turns), score = 136,885
CpuMemMT03 in 4,006 ms(177 turns), score = 132,551
CpuMemMT01 in 4,007 ms(177 turns), score = 132,518
CpuMemMT00 in 4,010 ms(173 turns), score = 129,426
CpuMemMT02 in 4,009 ms(174 turns), score = 130,207
CpuMemMT4 x4 in 4,014 ms, score = 523,916
CpuMemMT02 in 4,009 ms(147 turns), score = 110,002
CpuMemMT06 in 4,008 ms(149 turns), score = 111,526
CpuMemMT00 in 4,010 ms(143 turns), score = 106,982
CpuMemMT01 in 4,034 ms(148 turns), score = 110,064
CpuMemMT03 in 4,034 ms(151 turns), score = 112,295
CpuMemMT07 in 4,033 ms(151 turns), score = 112,323
CpuMemMT04 in 4,018 ms(147 turns), score = 109,756
CpuMemMT05 in 4,034 ms(148 turns), score = 110,064
CpuMemMT8 x8 in 4,040 ms, score = 879,207
CpuMemMT08 in 4,001 ms(84 turns), score = 62,984
CpuMemMT02 in 4,020 ms(81 turns), score = 60,447
CpuMemMT11 in 4,019 ms(81 turns), score = 60,462
CpuMemMT01 in 4,012 ms(83 turns), score = 62,063
CpuMemMT12 in 4,008 ms(78 turns), score = 58,383
CpuMemMT03 in 4,008 ms(80 turns), score = 59,880
CpuMemMT06 in 4,007 ms(80 turns), score = 59,895
CpuMemMT00 in 4,003 ms(76 turns), score = 56,957
CpuMemMT05 in 4,002 ms(80 turns), score = 59,970
CpuMemMT04 in 4,002 ms(79 turns), score = 59,220
CpuMemMT14 in 4,004 ms(80 turns), score = 59,940
CpuMemMT13 in 4,004 ms(82 turns), score = 61,438
CpuMemMT09 in 4,012 ms(77 turns), score = 57,577
CpuMemMT10 in 4,010 ms(81 turns), score = 60,598
CpuMemMT15 in 4,043 ms(84 turns), score = 62,329
CpuMemMT07 in 4,021 ms(81 turns), score = 60,432
CpuMemMT16 x16 in 4,151 ms, score = 930,137
DiskSingle in 5,009 ms(1 turns), score = 59 (windows disk is much slower than linux score=229)
```

### AMD A6-3650 APU
```
Start benchmark v2.2
CpuMemSingle in 6,013 ms(278 turns), score = 138,699 
CpuMemMT00 in 6,011 ms(74 turns), score = 36,932 
CpuMemMT01 in 6,020 ms(82 turns), score = 40,863 
CpuMemMT02 in 6,015 ms(73 turns), score = 36,408 
CpuMemMT03 in 6,027 ms(75 turns), score = 37,332 
CpuMemMT06 in 6,001 ms(80 turns), score = 39,993 
CpuMemMT04 in 6,032 ms(81 turns), score = 40,285 
CpuMemMT09 in 6,000 ms(76 turns), score = 38,000 
CpuMemMT07 in 6,023 ms(77 turns), score = 38,352 
CpuMemMT11 in 6,001 ms(76 turns), score = 37,993 
CpuMemMT10 in 6,022 ms(75 turns), score = 37,363 
CpuMemMT12 in 6,000 ms(81 turns), score = 40,500 
CpuMemMT08 in 6,039 ms(74 turns), score = 36,761 
CpuMemMT05 in 6,045 ms(78 turns), score = 38,709 
CpuMemMT13 in 6,014 ms(75 turns), score = 37,412 
CpuMemMT15 in 6,013 ms(77 turns), score = 38,416 
CpuMemMT14 in 6,047 ms(76 turns), score = 37,704 
CpuMemMT x16 in 6,164 ms, score = 598,637 
DiskSingle in 7,216 ms(5 turns), score = 207 
```

### Amazon Linux AMI (Xeon E5-2670 v2 @ 2.50GHz (1 cpu core))
```
Start benchmark v2.2
CpuMemSingle in 6,011 ms(436 turns), score = 217,601
CpuMemMT04 in 6,075 ms(21 turns), score = 10,370
CpuMemMT03 in 6,096 ms(11 turns), score = 5,413
CpuMemMT02 in 6,118 ms(11 turns), score = 5,393
CpuMemMT01 in 6,138 ms(11 turns), score = 5,376
CpuMemMT06 in 6,023 ms(22 turns), score = 10,957
CpuMemMT07 in 6,067 ms(22 turns), score = 10,878
CpuMemMT08 in 6,110 ms(22 turns), score = 10,801
CpuMemMT09 in 6,151 ms(22 turns), score = 10,729
CpuMemMT10 in 6,195 ms(22 turns), score = 10,653
CpuMemMT11 in 6,238 ms(22 turns), score = 10,580
CpuMemMT13 in 6,305 ms(22 turns), score = 10,467
CpuMemMT14 in 6,326 ms(18 turns), score = 8,536
CpuMemMT00 in 6,355 ms(12 turns), score = 5,664
CpuMemMT12 in 6,347 ms(16 turns), score = 7,562
CpuMemMT15 in 6,372 ms(11 turns), score = 5,178
CpuMemMT05 in 6,216 ms(23 turns), score = 11,100
CpuMemMT x16 in 6,400 ms, score = 135,000
DiskSingle in 6,461 ms(6 turns), score = 278
```
### Xeon L5408 4 core, sata2, windows
```
Start benchmark v2.2
CpuMemSingle in 6,014 ms(198 turns), score = 98,769
CpuMemMT03 in 6,015 ms(44 turns), score = 21,945
CpuMemMT08 in 6,015 ms(44 turns), score = 21,945
CpuMemMT04 in 6,015 ms(43 turns), score = 21,446
CpuMemMT07 in 6,028 ms(44 turns), score = 21,897
CpuMemMT13 in 6,069 ms(46 turns), score = 22,738
CpuMemMT09 in 6,033 ms(44 turns), score = 21,879
CpuMemMT11 in 6,061 ms(45 turns), score = 22,273
CpuMemMT05 in 6,021 ms(45 turns), score = 22,421
CpuMemMT06 in 6,001 ms(43 turns), score = 21,496
CpuMemMT10 in 6,044 ms(45 turns), score = 22,336
CpuMemMT00 in 6,085 ms(44 turns), score = 21,692
CpuMemMT12 in 6,039 ms(44 turns), score = 21,857
CpuMemMT02 in 6,077 ms(46 turns), score = 22,708
CpuMemMT01 in 6,047 ms(47 turns), score = 23,317
CpuMemMT15 in 6,014 ms(48 turns), score = 23,944
CpuMemMT14 in 6,020 ms(46 turns), score = 22,923
CpuMemMT x16 in 6,225 ms, score = 346,024
DiskSingle in 6,948 ms(1 turns), score = 43
```

### please add more here
contribute.
