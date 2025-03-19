package penpen.clientsidegive;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class ClientSideGiveClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("test_command").executes(context -> {
				context.getSource().sendFeedback(() -> Text.literal("Called /test_command."), false);
				context.getSource().getPlayer().getInventory().offer(new ItemStack(Items.DIAMOND, 1), false);
				return 1;
			}));
		});
	}

}

