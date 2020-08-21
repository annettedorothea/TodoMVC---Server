# TodoMVC---Server

This is the server for the acegen TodoMVC sample.

See [TodoMVC](https://todo.acegen.de/#).

It is written with Java, is based on the [de.acegen](https://github.com/annettedorothea/de.acegen) 
DSL and code generator and uses Dropwizard as http server framework.

See [TodoMVC---Client](https://github.com/annettedorothea/TodoMVC---Client) for the client project.

## Installation

- Get latest Ecplise.
- Install the [de.acegen](https://github.com/annettedorothea/de.acegen) Ecplise plugin from [http://acegen.de](http://acegen.de).
- Maven install the project dependencies.
- Create a PostgreSQL database.
- Create a yml file like [sample.yml](sample.yml).
- Run the migrations with `db migrate <your-yml-file>.yml` as argument.
- Start the application with the arguments `server <your-yml-file>.yml` as argument.

## Replay

If you want to replay scenarios and bugs in the browser you need to start a server in dev and another server in
replay mode. You also need a second database for the replay-server. 

Create a second yml-config file and set mode to replay:
```
    config:
      mode: REPLAY
      writeTimeline: ALWAYS 
```
Make sure you connect to the second database.

In order to save bugs and scenarios your dev-server needs to write the timeline:
```
    config:
      mode: DEV
      writeTimeline: ALWAYS 
```

## Execute the tests

If you want to execute the tests you should start the server in test mode:

```
config:
  mode: TEST
  writeTimeline: ALWAYS
```

Please note that the database is cleared on startup of the server.

When the test-server is running you can execute the tests.

## License
[ISC](License.txt)
