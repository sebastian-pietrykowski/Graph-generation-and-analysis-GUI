
projekt1: main.c graph.o data_structures.o dijkstra.o bfs.o generator.o
	cc -o projekt1 main.c graph.o data_structures.o dijkstra.o bfs.o generator.o -ggdb

tests: tests.c tests_auxiliary_functions.o tests_data_structures.o tests_generator.o tests_dijkstra.o graph.o data_structures.o dijkstra.o bfs.o generator.o
	cc -o tests tests.c tests_auxiliary_functions.o tests_data_structures.o tests_generator.o tests_dijkstra.o graph.o data_structures.o dijkstra.o bfs.o generator.o

.PHONY: clean

clean:
	rm *.o projekt1 tests
