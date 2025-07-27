![](https://raw.githubusercontent.com/RHX0R3/RHLeafDecay/refs/heads/main/rhleafdecay_paper.png)
<center> With <b>RHLeafDecay</b> all your floating leaves will disappear in no time.

This plugin supersedes its [Spigot](https://www.spigotmc.org/resources/%E2%98%84%EF%B8%8F-rhleafdecay-fast-and-smooth-leaf-decay-1-20-6-1-21-x.83581/) version.<br>
![](https://img.shields.io/bstats/servers/11383?color=green?label=Servers)
![](https://img.shields.io/bstats/players/11383?color=green?label=Players)
[![https://img.shields.io/discord/422802721280622594?color=%237289da%20&label=Discord&logo=Discord&logoColor=white](https://img.shields.io/discord/422802721280622594?color=%237289da%20&label=Discord&logo=Discord&logoColor=white)](https://raidersheaven.eu/discord)

Made with love in Germany 🇩🇪</center>

# Features
## <u>Decay Modes</u>
**Cluster Mode** (Default):  →  Breaks leaves in groups for faster decay.<br>
**Single Mode**:  →  Breaks leaves one by one for a slower, more natural decay.
## <u>Customizable Tick Rates</u>
break (Default: 8)  →  Controls the delay between leaf decay events.<br>
check (Default: 4)  →  Defines how often the plugin checks for decaying leaves.<br>
⚠️ Note: The break tick rate should never be below 5 to avoid performance issues!
## <u>Additional Effects</u>
**Drops:**  →  Leaves can drop items when they decay.<br>
**Particles:**  →  Adds visual effects to the leaf decay process.<br>
**Sounds:**  →  Plays sound effects for a more immersive experience.
## <u>World Filtering</u>
**Whitelist:**  →  Decay occurs only in specified worlds.<br>
**Blacklist:**  →  Leaves will not decay in excluded worlds.
# What does it look like in-game?

![](https://youtu.be/QmutQwsUZs0)
# Custom trees? World generators?
**RHLeafDecay** relies on Minecraft’s natural leaf decay. For it to work, leaves must be recognized as part of a tree and not be persistent.<p></p>
✅ Vanilla trees work because their leaves naturally decay after the trunk is removed.<br>
⚠️ Custom trees and world generators cause two major issues:<br>
````
- Leaves never decay if marked persistent  →  RHLeafDecay can’t remove them.
- Leaves vanish instantly if attached to a block that isn’t recognized as a tree trunk.
````

## <u>Why does this happen?</u>
**RHLeafDecay** checks if leaves are linked to a valid trunk before triggering decay. Many world generators mark leaves as persistent to prevent unwanted decay. Custom trees may use non-standard materials for trunks, making them unrecognizable to the plugin.

## <u>What can I do?</u>
**RHLeafDecay** only works with non-persistent leaves and standard tree trunks.
You can manually fix leaves using `/data merge block {persistent:false}`, but this isn’t practical on a large scale.<br>
If your world relies heavily on custom trees, **RHLeafDecay** <u>won’t work</u> as expected.
## <u>Conclusion</u>
If leaves aren’t detected as part of a real tree or are persistent, they either won’t decay at all or will disappear too quickly. For the best experience, use Vanilla trees with standard leaves. 🌿

# Support

If you need help with **RHLeafDecay**, you can join the [RaidersHeaven server](https://raidersheaven.eu/discord/) on Discord.
Just post inside the [#rhleafdecay-plugin](https://discord.com/channels/422802721280622594/931212161114587176) channel.<p></p>
Since support is provided exclusively by me, there may be delays and longer response times as I cannot read all messages immediately or only at certain times of the day.
Thank you for your understanding!
# Metrics collection

**RHLeafDecay** collects anonymous server statistics through [bStats](https://bstats.org/plugin/bukkit/RHLeafDecay/11383), an open-source statistics service for Minecraft software. You can opt out in ` plugins/bStats/config.yml ` at anytime.
![](https://bstats.org/signatures/bukkit/RHLeafDecay.svg)
