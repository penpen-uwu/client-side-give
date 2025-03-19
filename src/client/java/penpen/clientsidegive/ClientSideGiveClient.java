package penpen.clientsidegive;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.ItemStack;

public class ClientSideGiveClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("clientgive")
                    .then(ClientCommandManager.argument("item", ItemStackArgumentType.itemStack(registryAccess))
                            .executes(context -> {
                                ItemStack itemStack = ItemStackArgumentType.getItemStackArgument(context, "item").createStack(1, false);
                                context.getSource().getPlayer().getInventory().offer(itemStack, false);
                                return 1;
                            })
                            .then(ClientCommandManager.argument("count", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        ItemStack itemStack = ItemStackArgumentType.getItemStackArgument(context, "item").createStack(IntegerArgumentType.getInteger(context, "count"), false);
                                        context.getSource().getPlayer().getInventory().offer(itemStack, false);
                                        return 1;
                                    })
                            )
                    )
            );
        });
    }
}