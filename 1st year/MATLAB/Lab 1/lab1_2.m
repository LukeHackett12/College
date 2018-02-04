x = -2*pi:0.01:2*pi;
y = sin(x) + randn(1, length(x)) * 0.2;
plot(x, y, 'b');