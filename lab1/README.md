# TQS - Lab1

## Lab 1.2: Euromillions

Após a geração do relatório de code coverage usando o plugin JaCoCo, podemos ver que o coverage para a classe `BoundedSetOfNaturals` é apenas de 54%, o que não é o ideal.

![Code coverage](./images/code_coverage_before_1.png)

![Code coverage details](./images/code_coverage_before_2.png)

Através da última imagem podemos concluir que o método `fromArray` não está a ser testado, uma vez que apresenta um code coverage de 0%. O mesmo acontece com o método `intersects`, `size` e `hashCode`.

--- 
## What kind of unit test are worth writing for proper validation of BoundedSetOfNaturals?

Para testar a classe `BoundedSetOfNaturals` de forma a garantir que o seu comportamento é o esperado, devemos efetuar as seguintes verificações:

1. O size é 0 após a sua cronstrução, no caso de não ser utilizado o método `fromArray`;

2. Na construção de um `BoundedSetOfNaturals` através do método `fromArray`, o size e o maxSize são iguais ao número de elementos do array e todos os elementos do array encontram-se no `BoundedSetOfNaturals`;

3. Na construção de um `BoundedSetOfNaturals` através do método `fromArray` utilizando um array inválido, deve ser esperada uma exceção do tipo `IllegalArgumentException`;

4. Após inserção de um elemento repetido, verificar se é lançada uma exceção do tipo `IllegalArgumentException`;

5. Após a inserção de um elemento válido e o set não estar cheio, o set contém o elemento inserido e o size é incrementado;

6. Após a inserção de um elemento quando o size já é igual ao limite, verificar se é lançada uma exceção do tipo `IllegalArgumentException`;

7. Após a construção de um `BoundedSetOfNaturals` com um array de elementos onde pelo menos um é inválido, verificar se é lançada uma exceção do tipo `IllegalArgumentException`;

8. O hashcode de dois sets com exatamente os mesmos elementos `BoundedSetOfNaturals` são iguais;

9. O hashcode de dois sets com pelo menos um elemento distinto são diferentes;

9. A interseção é verdadeira quando é feita com um subconjunto do `BoundedSetOfNaturals` a testar;

10. A interseção é falsa quando é feita com um conjunto que não é subconjunto do `BoundedSetOfNaturals` a testar;


> Nota: Foi adicionado um getter do atributo `maxSize` na classe `BoundedSetOfNaturals` de modo a que seja possível testar se o maxSize é igual ao tamanho do array passado como argumento ao método `fromArray`.

<br>

## Code Coverage after adding new tests

Após a adição dos testes supracitados, a percentagem de code coverage aumentou para 96%. Não foram desenvolvidos mais testes de modo a alcançar a marca dos 100%, uma vez que nem todo o código tem interesse em ser testado, neste caso o método `equals`.


![Code coverage](./images/code_coverage_after_1.png)

![Code coverage details](./images/code_coverage_after_2.png)


Em situações reais, não existe qualquer interesse em que o code coverage seja de 100%, pois isso signficaria que código que não deveria ser testado está a ser testado. Exemplos deste tipo de código são os getters e setters, framework code, etc.