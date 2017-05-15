# From-File-Search-Engine
A simple file search engine in Java that uploads files and displays search results for a given input
Driver Program: HashSearch.java
Somefile.txt included for testing purposes
The other files are classes used in the driver.

Program allows the user to upload files. When a file is uploaded, all words in the file become SearchObjects. These hold information about the file in which the word was found, the line number, and the line of text itself. The user can then search for terms. Priority results (exact matches to the search query) will be displayed first.

All data structures are dynamically resized. HashMap has an ideal O(1) lookup.

This started as a project for my CS221 class, and I've made some modifications since then.
