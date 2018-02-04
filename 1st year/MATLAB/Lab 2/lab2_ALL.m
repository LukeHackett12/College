figure(1)
hold on;
xlim([-1 1])
ylim([-2 2])

x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:1
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')


figure(2)
hold on;
xlim([-1 1])
ylim([-2 2])
x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:3
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')

figure(3)
hold on;
xlim([-1 1])
ylim([-2 2])
x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:5
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')

figure(4)
hold on;
xlim([-1 1])
ylim([-2 2])
x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:10
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')

figure(5)
hold on;
xlim([-1 1])
ylim([-2 2])
x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:50
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')

figure(6)
hold on;
xlim([-1 1])
ylim([-2 2])
x = -1:0.0001:1;
y = square(2 * pi * x);
plot(x, y, 'r')

k = 1;
tempY = 0.0;
for i = 1:500
    tempY = tempY + sin(2 * pi * k * x)/k;
    k = k + 2;
end

yApprox = tempY * 4/pi;

plot(x, yApprox, 'b')
