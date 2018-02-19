load("array.mat");
%plot(y);

N = 1024;
H=fft(y,N);
F=fftshift(abs(H));
newX = -fs/2 : fs/N : fs/2-fs/N;
plot(newX,F);