window = 1;

for accuracy = [1, 3, 5, 10, 50, 500]
    subplot(3, 2, window)
    title("Approximation with " + accuracy + " sine functions");
    hold on;
    xlim([-1 1])
    ylim([-2 2])
    
    x = -1:0.0001:1;
    y = square(2 * pi * x);
    plot(x, y, 'r')

    k = 1;
    tempY = 0;
    for i = 1:accuracy
        tempY = tempY + sin(2 * pi * k * x)/k;
        k = k + 2;
    end

    yApprox = tempY * 4/pi;
    plot(x, yApprox, 'b')
    
    window = window + 1;
end