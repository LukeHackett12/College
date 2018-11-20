all: main.c huff.c huff.h encode.h encode.c decode.h decode.c
	gcc -O3 -Wall -g -o huff main.c huff.c encode.c decode.c -I. -lm

test: showcodes encode decode

showcodes:
	$(info Test Showcodes: )
	bash -c "./huff showcodes modern-prometheus-unix.txt > showcodes.student"	
	bash -c "if diff -q showcodes.expected showcodes.student; then echo test passed; else echo test failed; fi;"
	bash -c "echo"

encode:
	$(info Test Encode: )
	bash -c "echo test with a tiny file"
	bash -c "./huff encode modern-prometheus-unix.txt tiny-encoding.input tiny-encoding.student"
	bash -c "if diff -q tiny-encoding.expected tiny-encoding.student; then echo test tiny passed; else echo test tiny failed; fi;"
	bash -c "echo default encoding test"
	bash -c "./huff encode modern-prometheus-unix.txt encode.input encode.student"
	bash -c "if diff -q encode.expected encode.student; then echo test passed; else echo test failed; fi;"
	bash -c "echo"

decode:
	$(info Test Decode: )
	bash -c "./huff decode modern-prometheus-unix.txt decode.input decode.student"
	bash -c "if diff -q decode.expected decode.student; then echo test passed; else echo test failed; fi;"
	bash -c "echo"

clean:
	rm prog

.SILENT: test encode decode showcodes
