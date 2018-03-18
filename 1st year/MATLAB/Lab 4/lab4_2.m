[signal, samplingFrequency]=audioread('exercise2_piece.wav');
%sound(signal,samplingFrequency);
amplitudeModulatedSignal = ammod(signal, 30000, samplingFrequency);
amplitudeModulatedSignal = amplitudeModulatedSignal + 0.01*randn(length(amplitudeModulatedSignal), 1);
demodulatedAMSignal = amdemod(amplitudeModulatedSignal,30000, samplingFrequency);
%sound(demodulatedAMSignal, samplingFrequency);

frequencyModulatedSignal = fmmod(signal, 30000, samplingFrequency, 20000);
firstFrequencyModulatedSignal = frequencyModulatedSignal + 0.01*randn(length(frequencyModulatedSignal), 1);
firstDemodulatedFMSignal = fmdemod(firstFrequencyModulatedSignal, 30000, samplingFrequency, 20000);
%sound(firstDemodulatedFMSignal, samplingFrequency);

frequencyModulatedSignal = fmmod(signal, 30000, samplingFrequency, 50000);
secondFrequencyModulatedSignal = frequencyModulatedSignal + 0.01*randn(length(frequencyModulatedSignal), 1);
secondDemodulatedFMSignal = fmdemod(secondFrequencyModulatedSignal, 30000, samplingFrequency, 50000);
%sound(secondDemodulatedFMSignal, samplingFrequency);

N = length(signal);
x = -samplingFrequency/2 : samplingFrequency/N : samplingFrequency/2-samplingFrequency/N;
subplot(3, 1, 1);
F=fftshift(abs(fft(amplitudeModulatedSignal,N)));
plot(x,F);
ylim([0 1500]);
xticks([-5:5] * 10000);
title('AM spectrum');

subplot(3, 1, 2);
F=fftshift(abs(fft(firstFrequencyModulatedSignal,N)));
plot(x,F);
ylim([0 30000]);
xticks([-5:5] * 10000);
title('FM spectrum (freq-dev = 20KHz)');

subplot(3, 1, 3);
F=fftshift(abs(fft(secondFrequencyModulatedSignal,N)));
plot(x,F);
ylim([0 20000]);
xticks([-5:5] * 10000);
title('FM spectrum (freq-dev = 50KHz)');