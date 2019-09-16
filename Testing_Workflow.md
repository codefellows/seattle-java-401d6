# Writing Unit Tests

## Questions Unit Test Should Answer
1. What are you testing?
1. What should it do?
1. What is the actual output?
1. What is the expected output?

## Shoulds of Unit Testing
1. Should test a single aspect of a component
    - Usually this means test should contain only a single assert
1. Tests should not rely on running in any particular order
1. Should be able to run repeatedly with same results given same input
1. Should be fast
1. Should be run often
    - Many tools allow tests to run on file save, or even after every keystroke!
1. Prefer simple assert statements
    - Frameworks often contain advanced, and sometimes very handy, assertions. 
        - E.g. `assertRendersTemplate('home.html')`
        - These are awesome, but use sparingly. Simple asserts are usually more readable.


## Example Tests

### Given a function like...
```
def sum(a,b):
  """ sum two numeric arguments """
  
  if isinstance(a, (int, float)) and isinstance(b, (int, float)):
    return a + b

  raise TypeError('argument must be int or float')
```

### Test the 'Happy Path' cases
```
def test_should_sum_two_integers():
    expected = 3
    actual = sum(1,2)
    assert expected is actual, "sum of 1 and 2 should be 3"
```

```
def test_should_sum_two_larger_integers():
    expected = 1333332
    actual = sum(1234567,98765)
    assert expected is actual, "sum of 1234567 and 98765 should be 1333332"
```

### Test with less obvious values. E.g. zero and negative numbers

```
def test_should_sum_negative_numbers():
    expected = 3
    actual = sum(5,-2)
    assert expected is actual, "sum of 5 and -2 should be 3"
```

### Test default argument values as needed

```
def test_should_handle_single_argument():
    expected = 2
    actual = sum(2)
    assert expected is actual, "sum of 2 and nothing should be 2 because 2nd argument defaults to 0"
```

### Test acceptable argument types

**Note:** Different languages handle floating point precision differently. Adjust your assertion as needed.
```
def test_should_sum_floats():
    expected = 5.0
    actual = sum(3.5,1.5)
    assert expected == actual, "sum of 3.5 and 1.5 should be 5.0"
```

```
def test_should_sum_integer_and_float():
    expected = 5.5
    actual = sum(3.5,2)
    assert expected == actual, "sum of 3.5 and 2 should be 5.5"
```

### Test unsupported argument types

```
def test_should_accept_only_numbers():
    # consult docs for how your test framework handles testing exceptions
    with pytest.raises(TypeError):
        sum('1',3)
```
## References

- [What Exactly is a Unit in Unit Testing?](https://www.blinkingcaret.com/2016/04/27/what-exactly-is-a-unit-in-unit-testing/){:target="_blank"}
- [10 Tips](https://dzone.com/articles/10-tips-to-writing-good-unit-tests){:target="_blank"}
- [5 Questions Every Unit Test Must Answer](https://medium.com/javascript-scene/what-every-unit-test-needs-f6cd34d9836d){:target="_blank"}

