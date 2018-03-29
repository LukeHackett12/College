close all;
SNR_Range = 0:2:35;
SNR = 0;
QAM_Range=[4 16 64 256];
size = 10^6;
for QAM = 1 : length(QAM_Range)
    stream = randi([0 QAM_Range(QAM)-1], size, 1);
    mod = qammod(stream, QAM_Range(QAM));
    for SNR = 1 : length(SNR_Range)
        signal_noise=awgn(mod, SNR_Range(SNR), 'measured');
        Dem = qamdemod(signal_noise, QAM_Range(QAM));
        %BER(QAM, SNR)=((length(stream) - sum(stream==Dem))/length(stream));
        BER(QAM, SNR) = biterr(stream, Dem);
    end
end

semilogy(SNR_Range, BER(1,:),SNR_Range, BER(2,:),SNR_Range, BER(3,:), SNR_Range, BER(4,:));