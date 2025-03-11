# Dynamic Encounter Generator

This is a prototype of a dynamic encounter generator for a tabletop role-playing game.
Currently, especially for D&D 5e using the (unofficial) DnDBeyond API.

## Features

- Generate encounters based on the party's level and size
- Sync monsters from DnDBeyond
- Create custom monsters

## Future Features

- Save encounters
- Extract keywords from the monster description
- Create a better recommendation system for monsters
- Add more systems (e.g. Pathfinder 2e, D&D 3.5e) with automatic syncs
- Add export options for VTTs (e.g. Foundry VTT, Roll20)

## Installation

1. Clone the repository
2. Setup docker compose and start the db container using the provided compose.yml in the `deq-server` module
3. If you have a DnDBeyond account with some purchased content (or the limited free SRD monsters)
   1. Create a `application-local.yaml` file in the `deq-server` resources module.
   2. Add following content to the `application-local.yaml` file:
       ```yaml
       dndbeyond:
       cobaltToken: '<cobalt>'
       ```
   3. Replace `<cobalt>` with your cobalt token from
      DnDBeyond [How to get Cobalt token](https://docs.ddb.mrprimate.co.uk/docs/ddb-importer/initial-setup#cobalt-cookie)
   4. Start the server, preferably using the provided run configuration in your IDE
4. Otherwise, you can either create them yourself or use the provided dump file to populate the database with my monsters.


## Usage
- Currently, the server is running by default on `localhost:8080` 
- You can access the Swagger UI on `http://localhost:8080/swagger-ui/index.html` and use your preferred client to access the API.
- The frontend is still in a really early prototype and not yet usable, thus not included.