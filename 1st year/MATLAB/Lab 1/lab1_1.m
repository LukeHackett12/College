hold on;

x = -2*pi:0.1:2*pi;
y = cos(x);
plot(x, y, 'k');
x1 = -2*pi:0.1:2*pi;
y1 = sin(x1)*0.5;
stem(x1, y1, 'r');