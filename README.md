# WebSockets and Actors - A Sample

This is a sample "application" for how to connect a websocket with an actor.
Please note that it only contains the scaffolding for how to get started.
The actual business logic is up to you to fill in. 


## To get started

Start by cloning this repo:
  
`~> git clone git@github.com:henrikengstrom/funcwebdaysthlm.git`

The next step is to download Typesafe Activator: 

<http://typesafe.com/platform/getstarted>

Once you have downloaded the Activator you should fire it up:

`~> <activator-installation-dir>/activator ui`

Now select the "Find Existing" option and select the folder where you cloned the "funcwebdaysthlm" repo.

That's it! You are ready to go.

## Calling the service

Start Chrome (or your favorite websocket capable browser) and open a console.
On Mac you can open the Chrome console by pressing `Option-Command-I`.

Type the following:
  
```
var ws = new WebSocket("ws://locahost:9000/ws");
ws.onmessage = function(msg) { console.log("> " + msg.data) }
ws.send("Test1");
``` 

Now you should see the following output in the console:

```
> Found result for Test1 @ 1383215025695 
> Found result for Test1 @ 1383215027695 
> Found result for Test1 @ 1383215029695 
```

You can change the search by:

```
ws.send("NewSearchWord");
```

and now the output should instead be:

```
> Found result for NewSearchWord @ 1383215145694 
```

Type `ws.close();` to close down the websocket connection.


## Using the Typesafe Console to inspect your running app
If you want to get better insight into your application I encourage you to try out the Console that comes as a part of the Activator. 

Click on the `Run` tab and you should see a `Login` button. Click on this button to log in to get access to the Console functionality. Once you have logged in the button says `Restart with Console` and if you click on it the Console will start up and monitor the application. Click on the `Open the Console` link to open up the Console UI. **Please note that you must establish a websocket, as per the section above, before anything will show up in the Console.**

## Questions?
Please go to <http://www.typesafe.com> for more information about Play, Akka or Activator.
