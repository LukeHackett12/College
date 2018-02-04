window = 1;

figure(2);
for accuracy = [1, 2, 3, 5, 10, 50]   
    subplot(3, 2, window);
    title("Approximation with " + accuracy + " sine functions");
    
    hold on;
    xlim([-1 1]);
    ylim([-1 1]);
    
    x = -1:0.0001:1;
    y = sawtooth(2 * pi * (x + 0.25), 0.5);
    plot(x, y, 'r');
    
    xApprox = -1:0.0001:1;
    k = 0;
    tempY = 0.0;
    for i = 1:accuracy
        tempY = tempY + (-1)^k * sin(2 * pi * (2 * k + 1) * xApprox)/(2 * k + 1)^2;
        k = k + 1;
    end

    yApprox = tempY * 8/(pi^2);
    plot(xApprox, yApprox, 'b');
    
    window = window + 1;

end
