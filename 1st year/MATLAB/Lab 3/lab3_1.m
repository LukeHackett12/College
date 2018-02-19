fs=100;
time=2;
frequency=10;
x=0.1/fs:time-1/fs;
y=sin(2*pi*x*frequency);

count=1;
for N=[64,128,256]
    figure(count);
    H=fft(y,N);
    F=fftshift(abs(H));
    newX = -fs/2 : fs/N : fs/2-fs/N;
    plot(newX,F);
    count=count+1;
end