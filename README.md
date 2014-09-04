<html>
<body>
===============
<p><b>Let’s Do Lunch</b></p>

<p>
Peggy Piranha and Samuel Amherst Salmon III (Sam) are old friends looking to 
get together for lunch. They haven’t seen each other in ages, but are keen on 
crossing paths for lunch. But where could they meet? They’re flexible on the 
ultimate destination, but there are few constraints. 
Peggy and Sam live in a fairly complicated watershed, with many rivers running 
together and splitting apart. Sam has to keep moving upstream for spawning 
season. Peggy has dinner plans with a killer whale later in the day, and has to 
keep moving downstream. Fortunately, we have a map given to us by the 
Piscine Post Office, with each junction marked with an address. Each segment 
of river is listed as pairs of addresses, from upstream to downstream. Given the 
map and a list of possible starting locations for Peggy and Sam, give the list of 
locations where they could meet. Oh, and there are a few places they must 
avoid passing through, with waterfalls, rapids, and other unpleasantness. Your 
job is to list the possible addresses where they could meet, one per line and 
sorted alphabetically by address.</p>

<p><b>Input/Output</b></p>

<p>Input and output are simple text, via standard input and standard output, 
respectively. Input will be provided in the format shown in the example below: 
the list of address pairs forming the map, followed by the list of addresses to 
avoid, the list of possible starting locations for Peggy, and finally the list of 
possible starting locations for Sam.</p>

Sample Input  Sample Output  Map (for reference)

<p>Map:
a1 b1
a2 b1
a2 b2
a2 b3
b1 c1
b2 c2
b2 c3
b3 c3
Avoid:
b2
Peggy:
a2
Sam:
c2 c3</p>

<p><b>Sample Output</b>
a2
b3
c3 </p>

<p><b>Guidelines, corner cases, and other details</p></b>

1.  Assume that the input is always well-formed - no need to guard against 
bad input.

2.  Address labels are a string which can have any character other than a 
space or newline. For example, a1 or ThisIsAnAddress. Don’t think too 
hard about it, it’s just a string to uniquely identify the node. Address names 
are always unique, the same word always indicating the same location.

3.  If there are multiple locations to avoid, or multiple starting locations, they 
will be listed on a single line separated by spaces, e.g.
a1 a2 a3

4.  If there are no locations to avoid, there will be a blank line, e.g.
Avoid:
Peggy:
a1

5.  Peggy and Sam will each always have at least one possible starting 
location.

6.  The output should be a list of locations, one per line, sorted alphabetically. 
If there are no possible meeting locations, there is no output either.

7.  Do not print anything other than the expected output - no headers, status 
messages or anything else (it would interfere with automated testing).

8.  If a starting location appears in the locations to avoid, the location mu st be 
avoided, and should not be passed through.

9.  Cycles in the map are possible. It is a complicated watershed, after all.


</body>
</html>
