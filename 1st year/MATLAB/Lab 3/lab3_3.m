[notes, fs] = audioread('exercise notes.wav');
N = 16384;

tuneOne = notes(1:6800);
tuneTwo = notes(6800:10000);

figure(1);
H=fft(tuneOne,N);
F=fftshift(abs(H));
newX = -fs/2 : fs/N : fs/2-fs/N;
plot(newX,F);
xlabel('Frequency');
ylabel('Amplitude');

figure(2);
H=fft(tuneTwo,N);
F=fftshift(abs(H));
newX = -fs/2 : fs/N : fs/2-fs/N;
plot(newX,F);
xlabel('Frequency');
ylabel('Amplitude');