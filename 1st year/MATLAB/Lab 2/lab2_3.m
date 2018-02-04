window = 1;

for accuracy = [1, 3, 5, 10, 50, 500]
    subplot(3, 2, window)
    title("Approximation with " + accuracy + " sine functions");
    
    hold on;
    
    x = 1:1:accuracy;
    k = 1;
    for i = 1:accuracy
        stem(k, (4/k*pi), 'b');
        k = k + 1;
    end
    
    window = window + 1;
end