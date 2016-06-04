# Arcy
RC4 encryption Maven package for Java.

Arcy is a very naïve and off-the-cuff implementation of the RC4 stream cipher in Java. I'm not a cryptographer and nobody has looked over this work. Please *please* don't use this library for anything security-critical. It's barely unit tested and RC4 has known vulnerabilities if not used correctly. 

## Installation
You can pull this package into your Maven project straight from here using JitPack. Add JitPack as a repository first:

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then add a dependency on Arcy:

```
<dependencies>
    <dependency>
        <groupId>com.github.lambdacasserole</groupId>
        <artifactId>arcy</artifactId>
        <version>v1.0</version>
    </dependency>
</dependencies>
```

## Verification
This RC4 library does perform as expected against the verified test vectors [proposed by Subhamoy Maitra](http://www.crcnetbase.com/doi/abs/10.1201/b11310-14) in *RC4 Stream Cipher and Its Variants*. Uses the `int` primitive and not the `byte` primitive in Java. Casting from one to the other (if necessary) is left to the user:

| Key                                | Keystream                             |
|------------------------------------|---------------------------------------|
| `00000000000000000000000000000000` | `de188941a3375d3a8a061e67576e926d...` |
| `ffffffffffffffffffffffffffffffff` | `6d252f2470531bb0394b93b4c46fdd9c...` |
| `01010101010101010101010101010101` | `06080e0e182029293933495766768783...` |
| `d764c8cce93255c4478d7aa05d83f3ea` | `c86e2d580e675554423642f33a6468e9...` |
| `2a83e82681a22df7a04329387f7f2cd5` | `960c0b913786a18411b0e9e4a7499bbc...` |
| `e38248bb72dd1350b2994e61ad0f9509` | `6cca71a62bb276d9d9ebc853970fe9fe...` |
| `b540a80fbb1c787c244e0e189ee9deff` | `50a75801240ece2030f43a90e55319d6...` |
| `993a6e57d103ef8affc253c841689fd2` | `45b1ddd8eb16da566c435742f3370d43...` |

## Contributing
For most intents and purposes, Arcy is considered to fulfil its original use case. Bug fixes and suggestions are welcome, however, from any member of the community.
