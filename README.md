This mod replaces the default single‑tier gas boost in GregTech CEu with a fully expanded, multi‑tier gas handling system.
Instead of one recipe for one gas tier, each noble gas now provides its own specific consumption amount and unique processing speed multiplier, creating a more meaningful progression curve.

GregTech CEu normally uses a single “gas tier” for recipe acceleration.
This mod introduces eight distinct gas tiers, each with:
- A defined fluid consumption amount
- A duration multiplier applied to the base recipe time
- Full recipe generation for every tier
This makes gas usage more strategic, rewarding higher‑tier gases with faster processing at lower consumption.

Gas Tier Table
| Gas Tier     | Amount Consumed | Duration Multiplier |
|--------------|-----------------|---------------------|
| No Gas       | --              | Duration            |
| Nitrogen     | 1000 mB         | Duration * 0.80     |
| Helium       | 1000 mB         | Duration * 0.72     |
| Argon        | 850 mB          | Duration * 0.64     |
| Radon        | 700 mB          | Duration * 0.56     |
| Neon         | 550 mB          | Duration * 0.48     |
| Krypton      | 400 mB          | Duration * 0.40     |
| Xenon        | 250 mB          | Duration * 0.32     |
| Oganesson    | 100 mB          | Duration * 0.24     |

## Credits
- Recipe time/amount concept: [GTNH's GT5 Unofficial](https://github.com/GTNewHorizons/GT5-Unofficial).
- Build script foundation: [GregTech CEu Buildscript](https://github.com/GregTechCEu/GregTech).
