package xyz.nikitacartes.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import me.lucko.fabric.api.permissions.v0.Permissions;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.dedicated.command.DeOpCommand.deop;
import static xyz.nikitacartes.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyDeOpCommand {

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("easydeop")
                .requires(Permissions.require("easywhitelist.commands.easydeop", 4))
                .then(argument("targets", string())
                        .executes(ctx ->
                                deop(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets"))))));
    }
}
