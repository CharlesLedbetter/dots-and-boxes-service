# Dots and Boxes Service

* [Overview](#overview)
* [To-dos](#to-dos)


This Project is a work in progress, see [To-dos section](#to-dos).

## Overview

A Service that provides a way for 2 to 10 players to play a game of 
[boxes and dots](https://en.wikipedia.org/wiki/Dots_and_Boxes)
through a RESTful API interface.

> This project has not been published to the npm registry. It was 
created primarily for the author as a reference implementation of
a RESTful web service using [kotlin](https://kotlinlang.org/), and
serving up responses using the 
[Siren mediatype](https://github.com/kevinswiber/siren).

## TO-DOs
- finish implementing error handling
- add OpenApi spec
- add tests
- containerize with kubernetes and Docker
- decide on and add user authentication 
(probably rh-sso or OAuth2 but since this is a toy app and does not 
need strong security maybe just an api-key)
