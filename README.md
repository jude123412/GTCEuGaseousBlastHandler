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
| Nitrogen     | 1000 mB         | Duration * 0.90     |
| Helium       | 900 mB          | Duration * 0.80     |
| Neon         | 800 mB          | Duration * 0.70     |
| Argon        | 700 mB          | Duration * 0.60     |
| Krypton      | 600 mB          | Duration * 0.50     |
| Xenon        | 500 mB          | Duration * 0.40     |
| Oganesson    | 250 mB          | Duration * 0.25     |

Stainless Steel Example
| Gas Tier     | Resulting Duration (ticks) |
|--------------|----------------------------|
| No Gas       | 1100                       |
| Nitrogen     | 1100 * 0.90 = 990          |
| Helium       | 1100 * 0.80 = 880          |
| Neon         | 1100 * 0.70 = 770          |
| Argon        | 1100 * 0.60 = 660          |
| Krypton      | 1100 * 0.50 = 550          |
| Xenon        | 1100 * 0.40 = 440          |
| Oganesson    | 1100 * 0.25 = 275          |

