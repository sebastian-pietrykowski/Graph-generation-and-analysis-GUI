projekt1: main.c graph.o data_structures.o dijkstra.o bfs.o generator.o
	cc -o projekt1 main.c graph.o data_structures.o dijkstra.o bfs.o generator.o

tests: tests.c tests_auxiliary_functions.o tests_data_structures.o tests_generator.o tests_dijkstra.o graph.o data_structures.o dijkstra.o bfs.o generator.o tests_bfs.o tests_graph.o
	cc -o tests tests.c tests_auxiliary_functions.o tests_data_structures.o tests_generator.o tests_dijkstra.o graph.o data_structures.o dijkstra.o bfs.o generator.o tests_bfs.o tests_graph.o

.PHONY: clean

clean:
	rm *.o projekt1 tests

