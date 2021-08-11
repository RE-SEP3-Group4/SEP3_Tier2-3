package com.example.tier3.network;

import com.google.gson.Gson;
import com.example.tier3.database.UserRepository;
import com.example.tier3.domain.User;
import lombok.AllArgsConstructor;

import static com.example.tier3.network.Response.ResponseStatus.NOT_FOUND;
import static com.example.tier3.network.Response.ResponseStatus.OK;

@AllArgsConstructor
public class UserController implements Controller {

    private UserRepository repo;
    private Gson gson;

    @Override
    public Response handle(Request req) {
        User user = null;
        Object resp = null;
        switch (req.getOperation()) {

            case GET:
                if (null != req.getPayload()) {
                    user = gson.fromJson(req.getPayload(), User.class);

                    if (null != user.getId()) {
                        resp = repo.findUserById(user.getId());
                    } else if (null != user.getUsername()) {
                        resp = repo.findUserByUsernameAndPassword(
                                user.getUsername(),
                                user.getPassword());
                    }
                    if (null == resp)
                        return new Response(NOT_FOUND, "No such user");
                }
                resp = repo.findAllUsers();
                break;
            case CREATE:
                user = gson.fromJson(req.getPayload(), User.class);
                resp = repo.createUser(user.getUsername(), user.getPassword());
                break;
            case UPDATE:
                user = gson.fromJson(req.getPayload(), User.class);
                resp = repo.updateUser(user.getId(), user.getUsername(), user.getPassword());
                break;
            case DELETE:
                user = gson.fromJson(req.getPayload(), User.class);
                resp = repo.deleteUser(user.getId());
                break;
        }
        return new Response(OK, gson.toJson(resp));
    }
}
