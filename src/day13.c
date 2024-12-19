#include <stdio.h>

#define FILE_NAME "input/13.txt"
#define SIZE 140

int main() {
    FILE* f = fopen(FILE_NAME, "r");

    long tokens = 0, tokens2 = 0;

    while (!feof(f)) {
        long ax, ay, bx, by, px, py;
        fscanf(f, "Button A: X+%ld, Y+%ld ", &ax, &ay);
        fscanf(f, "Button B: X+%ld, Y+%ld ", &bx, &by);
        fscanf(f, "Prize: X=%ld, Y=%ld ", &px, &py);

        long numeratorX = px*by - py*bx;
        long denomiatorX = ax*by - ay*bx;
        
        long numeratorY = px*ay - py*ax;
        long denomiatorY = ay*bx - ax*by;

        if (numeratorX % denomiatorX == 0 && numeratorY % denomiatorY == 0) {
            long a = numeratorX / denomiatorX;
            long b = numeratorY / denomiatorY;
            tokens += 3*a + b;
        }

        px += 10000000000000;
        py += 10000000000000;

        numeratorX = px*by - py*bx;
        denomiatorX = ax*by - ay*bx;
        
        numeratorY = px*ay - py*ax;
        denomiatorY = ay*bx - ax*by;

        if (numeratorX % denomiatorX == 0 && numeratorY % denomiatorY == 0) {
            long a = numeratorX / denomiatorX;
            long b = numeratorY / denomiatorY;
            tokens2 += 3*a + b;
        }
    }

    printf("Part one: %ld\n", tokens);
    printf("Part two: %ld\n", tokens2);
}
