[notes, fs] = audioread('exercise notes.wav');
N = 16384;

%plot(notes);

tuneOne = notes(1:6765);    %tune one is from 1-6765 x val
tuneTwo = notes(6765:10000);%tune two is from 6765-10000 x val

figure(1);
H=fft(tuneOne,N);   %perform fast fourier transfom on the note
F=fftshift(abs(H)); %shift it to the centre of the graph
newX = -fs/2 : fs/N : fs/2-fs/N;    %array of new x vals to graph
plot(newX,F);   %plot fft
xlabel('Frequency');    
ylabel('Amplitude');

figure(2);
H=fft(tuneTwo,N);   %perform fast fourier transfom on the note
F=fftshift(abs(H)); %shift it to the centre of the graph
newX = -fs/2 : fs/N : fs/2-fs/N;    %array of new x vals to graph
plot(newX,F);   %plot fft
xlabel('Frequency');
ylabel('Amplitude');