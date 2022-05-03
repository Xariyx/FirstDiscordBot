import listeners.ChatListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {
        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);


        jdaBuilder.addEventListeners(
                new ChatListener()
        );
        JDA jda = jdaBuilder.build();

        jda.awaitReady();

//        if(true){
//            CommandListUpdateAction commands = jda.updateCommands();
//            commands.complete().get(0).delete().queue();
//            commands.queue();
//        }

    }

}
