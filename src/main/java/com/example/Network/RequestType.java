package com.example.Network;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.Contoller.GameController;
import com.example.Contoller.UserController;
import com.example.Model.Game;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.google.gson.Gson;

public enum RequestType{
	LOGIN {
		@Override
		public Response handle(Request request) throws Exception {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String uuid = request.getUUID();
			UserController.getInstance().loginUser(username, password, uuid);
			return new Response(0, "login successful");
		}
	},
	REGISTER {
		@Override
		public Response handle(Request request) throws Exception {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			UserController.getInstance().registerUser(username, password, nickname);
			return new Response(0, "register successful");
		}
	},
	START_GAME {
		@Override
		public Response handle(Request request) throws Exception {
			String usernamesJson = request.getParameter("usernamesJson");
			long seed = Long.parseLong(request.getParameter("seed"));
			Gson gson = new Gson();
			ArrayList<String> usernames = new ArrayList<>(Arrays.asList(gson.fromJson(usernamesJson, String[].class)));
			GameController.getInstance().startNewGame(usernames, seed);
			// return null; // TODO
		}
	},
	JOIN_GAME {
		@Override
		public Response handle(Request request) throws Exception {
			Game game = Game.getInstance();
			User user = UserController.getInstance().getUserByUUID(request.getUUID());
			if (user==null) return new Response(1, "invalid uuid");
			if (game==null) return new Response(1, "game hasn't started yet");
			if (!game.isUserPlayingGame(user)) return new Response(1, "you are not invited to this game");
			return new Response(0, "Welcome to this Game!");
		}
	},
	GAME_ACTION {
		@Override
		public Response handle(Request request) throws Exception {
			User user = UserController.getInstance().getUserByUUID(request.getUUID());
			String queryJson = request.getParameter("queryJson");
			UserActionQuery query = UserActionQuery.fromJson(queryJson);
			String username = query.username;
			if (!user.getUsername().equals(username)) throw new Exception("you("+username+") cant do action for user: "+user.getUsername());
			query.validate();
			GameController.getInstance().handleQueryFromView(query);
			return new Response(0, "successful!");
		}
	},
	GET_ACTIONS {
		@Override
		public Response handle(Request request) {
			return new Response(0, "", Game.getInstance().gameHistory.toJson());
		}
	};

	public abstract Response handle(Request request) throws Exception;
}
