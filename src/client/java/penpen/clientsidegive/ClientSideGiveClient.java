package penpen.clientsidegive;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.item.ItemStack;

public class ClientSideGiveClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommands.literal("clientgive")
                .then(ClientCommands.argument("item", ItemArgument.item(registryAccess))
                        .executes(context -> {
                            ItemStack itemStack = ItemArgument.getItem(context, "item").createItemStack(1);
                            context.getSource().getPlayer().getInventory().placeItemBackInInventory(itemStack, false);
                            return 1;
                        })
                        .then(ClientCommands.argument("count", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    ItemStack itemStack = ItemArgument.getItem(context, "item").createItemStack(IntegerArgumentType.getInteger(context, "count"));
                                    context.getSource().getPlayer().getInventory().placeItemBackInInventory(itemStack, false);
                                    return 1;
                                })
                        )
                )
        ));
    }
}