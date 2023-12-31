package dev.zanckor.multirespawnlocation.server.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.zanckor.multirespawnlocation.api.respawnpointmanager.RespawnPoint;
import dev.zanckor.multirespawnlocation.server.capability.PlayerData;
import dev.zanckor.multirespawnlocation.server.capability.PlayerDataProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BedBlock;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.LOGGER;
import static dev.zanckor.multirespawnlocation.MultiRespawnLocation.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID)
public class CommandHandler {

    @SubscribeEvent
    public static void commandRegistry(RegisterCommandsEvent e) {
        LOGGER.debug("MultiRespawnLocation Commands registered");

        e.getDispatcher().register(Commands.literal("multirespawn")
                .requires((player) -> player.hasPermission(3))
                .then(Commands.literal("teleport")
                        .then(Commands.argument("respawnName", StringArgumentType.string())
                                .suggests(CommandHandler::availableRespawnPoints)
                                .executes((ctx ->
                                        teleportToRespawnPoint(ctx, StringArgumentType.getString(ctx, "respawnName"))
                                ))))

                .then(Commands.literal("remove")
                        .then(Commands.argument("respawnName", StringArgumentType.string())
                                .suggests(CommandHandler::availableRespawnPoints)
                                .executes((ctx ->
                                        removeRespawnPoint(ctx, StringArgumentType.getString(ctx,"respawnName"))))
                        ))

        );
    }


    private static CompletableFuture<Suggestions> availableRespawnPoints(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        final Player PLAYER = ctx.getSource().getPlayer();
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(PLAYER);

        PLAYER_DATA.getRespawnPoint().forEach(respawnPoint -> {
            builder.suggest(respawnPoint.getName());
        });

        return builder.buildFuture();
    }


    private static int teleportToRespawnPoint(CommandContext<CommandSourceStack> ctx, String respawnName) {
        final Player PLAYER = ctx.getSource().getPlayer();
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(PLAYER);

        //Checks what respawnPoint is equal to selected, and teleport player
        for (RespawnPoint respawnPoint : PLAYER_DATA.getRespawnPoint()) {
            if (respawnPoint.getName().equals(respawnName)) {
                final BlockPos BLOCK_POS = respawnPoint.getPosition().toBlockPos();

                //Check if bed is still there
                if (PLAYER.level().getBlockState(BLOCK_POS).getBlock() instanceof BedBlock) {
                    PLAYER.teleportTo(BLOCK_POS.getX(), BLOCK_POS.getY(), BLOCK_POS.getZ());
                    return 1;
                }
            }
        }

        return 0;
    }

    private static int removeRespawnPoint(CommandContext<CommandSourceStack> ctx, String respawnName) {
        final Player PLAYER = ctx.getSource().getPlayer();
        final PlayerData PLAYER_DATA = PlayerDataProvider.getPlayer(PLAYER);

        PLAYER_DATA.removeRespawnPoint(respawnName);

        return 1;
    }
}
