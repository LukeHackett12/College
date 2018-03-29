close all;
QAM = 16;
stream = randi([0 QAM-1],10^6,1);
mod = qammod(stream, QAM); 

SNR=16;
signal_noise=awgn(mod, SNR, 'measured');
const=comm.ConstellationDiagram('ShowReferenceConstellation',false, 'XLimits', [-4 4], 'YLimits', [-4 4]);
step(const, signal_noise);

SNR=20;
signal_noiseB=awgn(mod, SNR, 'measured');
constB=comm.ConstellationDiagram('ShowReferenceConstellation',false, 'XLimits', [-4 4], 'YLimits', [-4 4]);
step(constB, signal_noiseB);