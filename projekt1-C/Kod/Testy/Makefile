tests: tests_auxiliary_functions.o tests_data_structures.o
	cc -o tests tests.c tests_auxiliary_functions.o tests_data_structures.o

tests_auxiliary_functions.o: tests_auxiliary_functions.c tests_auxiliary_functions.h
tests_data_structures.o: tests_data_structures.c tests_data_structures.h data_structures.o
data_structures.o: ../data_structures.c ../data_structures.h

.PHONY: clean

clean:
	-rm *.o tests
