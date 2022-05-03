package listeners;

import model.User;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

    UserService userService = new UserService();

    //pan kiedys stanal nad brzegiem
    //Szukal ludzi
    //gotowych pojsc za nim
    //by lowic serca
    //slow bozych prawdą

    //O panie
    //to ty na mnie spojrzales
    //twoje usta dziś wyrzekly me imie
    //Swoja barke pozostawiam na brzegu
    //Razem z tobą nowy zacznę dziś łów

    //O panie
    //to ty na mnie spojrzales
    //twoje usta dziś wyrzekly me imie
    //Swoja barke pozostawiam na brzegu
    //Razem z tobą nowy zacznę dziś łów
    //



    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message message = event.getMessage();
        if (message.getAuthor().isBot()) {
            return;
        }

        if(!message.getContentRaw().toLowerCase().contains("xartest")){
            return;
        }

        String[] args = message.getContentRaw().toLowerCase().split(" ");

        if(args.length<3){
            return;
        }

        String command = args[1];

        String userID_String = args[2]
            .replace("<", "")
            .replace("@", "")
            .replace("!", "")
            .replace(">", "");

        if(userID_String.length()!=18){
            return;
        }

        long userID = 0;

        try{
            userID = Long.parseLong(userID_String);
        }catch (NumberFormatException exception){
            return;
        }

        Long balance = null;
        try {
            balance = Long.parseLong(args[3]);
        }catch (NumberFormatException exception){
            return;
        }catch (IndexOutOfBoundsException ignored){
        }

        switch (command) {
            case "delete", "del" -> {
                boolean bool = userService.deleteUser(userID);
                message.reply(String.valueOf(bool)).queue();
            }
            case "add" -> {
                if (balance == null) {
                    return;
                }
                User user = userService.addUser(userID, balance);
                message.reply(user.toString()).queue();
            }

            case "update", "upd" -> {
                if (balance == null) {
                    return;
                }
                User user = userService.updateUser(userID, balance);
                if(user == null){
                    message.reply("User doesnt exist in database").queue();
                    return;
                }
                message.reply(user.toString()).queue();
            }

            case "get" -> {
                User user = userService.getUser(userID);
                if(user == null){
                    message.reply("User doesnt exist in database").queue();
                    return;
                }
                message.reply(user.toString()).queue();
            }
            default -> message.reply("Bad option!").queue();
        }





    }
}
