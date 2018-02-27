clear all;
f=10;   %frequency ten hz
fs=100; %sampling frequency of 100
time=2; %time to cover 20 periods
x=0:1/fs:time-1/fs; % array of x vals to graph
y=sin(2*pi*f*x); % sin wave y vals
%figure(1);
%plot(x,y);

count=1;
for N=[64,128,256]
    figure(count);
    H=fft(y,N); %fft sin wave with desired N
    F=fftshift(abs(H)); %shift to centre
    newX = -fs/2 : fs/N : fs/2-fs/N; %array of new x values
    plot(newX,F); %plot fft
    count=count+1;
end