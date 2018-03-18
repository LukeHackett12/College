[signal, samplingFrequency]=audioread('exercise1_piece.wav');
N = length(signal);
subplot(3, 1, 1);
F=fftshift(abs(fft(signal,N)));
x = -samplingFrequency/2 : samplingFrequency/N : samplingFrequency/2-samplingFrequency/N;
plot(x,F);
xticks([-5:5] * 10000);
title('Unmodulated signal spectrum');

subplot(3, 1, 2);
F=fftshift(abs(fft(ammod(signal, 30000, samplingFrequency),N)));
plot(x,F);
xticks([-5:5] * 10000);
title('AM spectrum');

subplot(3, 1, 3);
F=fftshift(abs(fft(fmmod(signal, 30000, samplingFrequency, 10000),N)));
plot(x,F);
xticks([-5:5] * 10000);
title('FM spectrum');