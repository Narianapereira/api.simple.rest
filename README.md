# api.simple.rest

This is a simple API using Java and SpringBoot to return the following entities:
results = {
  pessoas: [{id:1, nome: "Marcelo"}, {id:2, nome: "João"}, {id:3, nome: "Maria"}],
  carros: [{id:1, modelo: "Fusca"}, {id:2, modelo: "Gol"}, {id:3, modelo: "Palio"}],
  animais: [{id:1, nome: "Cachorro"}, {id:2, nome: "Gato"}, {id:3, nome: "Papagaio"}]
}.

This was part of a project of the Software Engineering Specialization of PUC Minas.
There is no auth required, and the endpoints are "/people", "/car" and "/animal".
The api implements a logic of client-side cache managing, that returns the code 304 in case no updates were made in the entities.

