package xyz.nikitacartes.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import me.lucko.fabric.api.permissions.v0.Permissions;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.command.argument.MessageArgumentType.getMessage;
import static net.minecraft.command.argument.MessageArgumentType.message;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.dedicated.command.BanCommand.ban;
import static xyz.nikitacartes.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyBanCommand {

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("easyban")
                .requires(Permissions.require("easywhitelist.commands.easyban", 3))
                .then((argument("targets", string())
                        .executes(ctx ->
                                ban(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets")), null)))
                        .then(argument("reason", message())
                                .executes(ctx ->
                                        ban(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets")), getMessage(ctx, "reason"))))));
    }
}
