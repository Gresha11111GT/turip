package me.gresha_11111_gt.minecraftplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import java.util.List;

public class MinecraftPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Регистрация PacketAdapter для обработки пакетов при подключении игрока
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Client.SETTINGS) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket(); // Получение пакета
                List<String> mods = extractModsFromPacket(packet); // Извлечение модов из пакета
                getLogger().info("Player " + event.getPlayer().getName() + " has installed mods: " + mods); // Вывод модов в консоль
            }
        });
    }

    private List<String> extractModsFromPacket(PacketContainer packet) {
        List<String> mods = null;
        if (packet.getType() == PacketType.Play.Client.SETTINGS) {
            // Проверяем тип пакета
            mods = packet.getStrings().getValues();
        }
        return mods;
    }
}