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

### E2180 dual core 2G Pentium
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



### OrangePi PC (H3)
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

### banabapi (arm)
```
> multiThreadBench x4 finished in 16419, score = 367.20373048067046
```

### AMD A6-3650 APU
```
> benchmark:CpuMem finished in 10000, turn = 435 score = 130500.0
> benchmark:CpuMem finished in 10003, turn = 409 score = 122663.2010396881
> benchmark:CpuMem finished in 10006, turn = 409 score = 122626.42414551269
> benchmark:CpuMem finished in 10012, turn = 413 score = 123751.49820215741
> multiThreadBench x4 finished in 10023, score = 124885.28084683954
> Start benchmark:Disk
> disk refvalue=70176
> disk refvalue=70176
> disk refvalue=70176
> benchmark:Disk finished in 11224, turn = 8 score = 213.82751247327155
```

### Amazon Linux AMI
```
benchmark:CpuMem finished in 10015, turn = 141 score = 42236.64503245132
benchmark:CpuMem finished in 10015, turn = 126 score = 37743.384922616075
benchmark:CpuMem finished in 10015, turn = 142 score = 42536.19570644034
benchmark:CpuMem finished in 10029, turn = 37 score = 11067.903081064911
multiThreadBench x4 finished in 10036, score = 33396.032185643155
Start benchmark:Disk
disk refvalue=70176
disk refvalue=70176
disk refvalue=70176
benchmark:Disk finished in 10252, turn = 10 score = 292.6258291065158
```

### please add more here
contribute.
