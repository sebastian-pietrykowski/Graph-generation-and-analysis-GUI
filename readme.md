# Graph-generation-and-analysis-GUI
A program with a graphical interface enabling the generation of different types of graphs, their visualization, connectivity checking and finding shortest paths.

## Table of Contents
* [Background](#background)
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [File format](#file-format)


## Background
This project was created as a part of a course at the Warsaw University of Technology by Sebastian Pietrykowski and Paweł Borkowski.


## General Information
The project provides a user-friendly application for generating, visualizing, and analyzing graphs. It supports the generation of complete, connected, and random graphs with customizable weight ranges. The graphs are displayed on the screen with numbered vertices and colored edges. Users can find the shortest path between vertices, visualize paths and verify graph connectivity. File operations for loading and saving graphs are also available. The project aims to facilitate graph exploration and analysis in a user-friendly manner. User interface is only available in Polish.


## Technologies Used
- Java - version 1.8
- JUnit - version 4
- JavaFX - version 18
- Maven - version 4.0.0


## Features
The program provides the following functionalities:
- Graph Generation:
    - dimensions of the graph specified by providing number of rows and columns,
    - customizable range of weights,
    - generates a complete graph,
    - generates a connected graph,
    - generates a random graph.
- Graph Visualization:
    - displays the generated graph on a screen,
    - vertices are numbered,
    - edges are colored by its weight based on a gradient scale,
    - separate window to manage determined paths,
    - displays paths by its colors,
    - displays particular path by coloring its edges by weights and coloring vertices by shortest distances to them from source vertex.
- Graph Analysis:
    - finds the shortest possible path between any two vertices of the graph,
    - displays the determined paths on the graph visualization,
    - verifies the connectivity of the graph.
- File Operations:
    - loading graph from a file,
    - saving graph to a file.


## Screenshots
![Generowanie grafu losowego](img/Generowanie-grafu-losowego.png)
![Generowanie grafu pelnego](img/Generowanie-grafu-pelnego.png)
![Generowanie grafu spojnego](img/Generowanie-grafu-spojnego.png)
![Sprawdzanie spojnosci grafu 2](img/Sprawdzanie-spojnosci-grafu-2.png)
![Wyznaczanie sciezki 2](img/Wyznaczanie-sciezki-2.png)
![Wyznaczone sciezki](img/Wyznaczone-sciezki.png)
![Zaznaczone wezly](img/Zaznaczone-wezly.png)

## File Format.
An example graph representation is provided below:
```
3 2
1 :0.8864916775696521 3 :0.2187532451857941
0 :0.2637754478952221 4 :0.6445273453144537 2 :0.4630166785185348
4 :0.8650384424149676 0 :0.42932761976709255
2 :0.9452864187437506
```
The two numbers at the beginning of the file represent the number of columns and rows. In the example, the graph has 3 columns, 2 rows and a total number of 6 vertices numbered from 0 to 5.
Each line represents a vertex, starting from vertex number 0. The numbers in each line indicate the vertices that the edges connect to. The weights for each edge are provided after the ":" prefix. Each entry in a line is separated by a space.
In the given file:
- There are edges from vertex 0 to vertices 1 (weight: 0.8864916775696521) and 3 (weight: 0.2187532451857941).
- There are edges from vertex 1 to vertices 0 (weight: 0.2637754478952221), 4 (weight: 0.6445273453144537) and 2 (weight: 0.4630166785185348).
- There are no edges from vertex 2 to any other vertex.
- There are edges from vertex 3 to vertices 4 (weight: 0.8650384424149676) and 0 (weight: 0.42932761976709255).
- There are no edges from vertex 4 to any other vertex.
- There is an edge from vertex 5 to vertex 2 (weight: 0.9452864187437506).
Note:
- The weights in the graph cannot be negative.
- The graph may only have edges between vertices that are adjacent vertically or horizontally.

