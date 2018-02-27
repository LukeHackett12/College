load("array.mat");
%plot(y);

H=fft(y,N); %fft with y vals and N from array.mat
F=fftshift(abs(H)); %shift the peak to centre of the image
newX = -fs/2 : fs/N : fs/2-fs/N; %array of new x vals
plot(newX,F); %plot fft