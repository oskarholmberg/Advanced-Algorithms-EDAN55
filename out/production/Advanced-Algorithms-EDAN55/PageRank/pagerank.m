%% Construct probability matrix P

fileId = fopen('data/tiny.txt', 'r');
line = fgetl(fileId);
P = zeros(str2num(line));
line = fgetl(fileId);
while not(isempty(line))
    line = strtrim(line);
    edges = strread(line);
    [row, col] = size(edges);
    for i = 1 : col : 2
        i
        P(edges(i) + 1, edges(i+1) + 1) = P(edges(i) + 1, edges(i+1) + 1) + 1;
    end
    line = fgetl(fileId);
end
P;
fclose(fileId);