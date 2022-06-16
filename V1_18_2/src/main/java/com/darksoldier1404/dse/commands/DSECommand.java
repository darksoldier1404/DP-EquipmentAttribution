package com.darksoldier1404.dse.commands;

import com.darksoldier1404.dse.EquipmentAttribution;
import com.darksoldier1404.dse.functions.DSEFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class DSECommand implements CommandExecutor, TabCompleter {
    private final EquipmentAttribution plugin = EquipmentAttribution.getInstance();
    private final String prefix = plugin.prefix;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(prefix + "§c권한이 없습니다.");
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "§c플레이어만 사용 가능합니다.");
            return false;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(prefix + "/dse set <attribute> <amount> - 손에 들고있는 장비의 어트리뷰트 값을 설정합니다.");
            p.sendMessage(prefix + "/dse add <attribute> <amount> - 손에 들고있는 장비의 어트리뷰트 값을 추가합니다.");
            p.sendMessage(prefix + "/dse sub <attribute> <amount> - 손에 들고있는 장비의 어트리뷰트 값을 뺍니다.");
            p.sendMessage(prefix + "/dse del <attribute> - 손에 들고있는 장비의 어트리뷰트 값을 제거합니다.");
            p.sendMessage(prefix + "/dse delall - 손에 들고있는 장비의 모든 어트리뷰트 값을 제거합니다.");
            return false;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "어트리뷰트 타입을 입력해주세요.");
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(prefix + "어트리뷰트 값을 입력해주세요.");
                return false;
            }
            DSEFunction.setAttribution(p, args[1], args[2]);
            return false;
        }
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "어트리뷰트 타입을 입력해주세요.");
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(prefix + "어트리뷰트 값을 입력해주세요.");
                return false;
            }
            DSEFunction.addAttribution(p, args[1], args[2]);
            return false;
        }
        if (args[0].equalsIgnoreCase("sub")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "어트리뷰트 타입을 입력해주세요.");
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(prefix + "어트리뷰트 값을 입력해주세요.");
                return false;
            }
            DSEFunction.subAttribution(p, args[1], args[2]);
            return false;
        }
        if(args[0].equalsIgnoreCase("del")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "어트리뷰트 타입을 입력해주세요.");
                return false;
            }
            DSEFunction.removeAttribution(p, args[1]);
            return false;
        }
        if(args[0].equalsIgnoreCase("delall")) {
            DSEFunction.removeAllAttribution(p);
            return false;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(!sender.isOp()) return null;
        if(args.length == 1) {
            return Arrays.asList("SET", "ADD", "SUB", "DEL");
        }
        if(args.length == 2) {
            return Arrays.asList("DAMAGE", "ARMOR", "HEALTH", "CRITICAL_CHANCE", "CRITICAL_AMOUNT", "SPEED", "JUMP_STRENGTH", "REQUIRE_LEVEL");
        }
        return null;
    }
}
