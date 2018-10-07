using SparseArrays

EDGE_FILE1_PATH = "f1.txt"
EDGE_FILE2_PATH = "f2.txt"

# Count the number of vertices
vertex_counter = 626892

# Triplets to store edges
I = Int64[]
J = Int64[]
V = Int64[]

# Loop through the edges, and fill the triplets with (i, j, 1) format.
# Increase the nodes id by 1 to match Julias array indices.
open(EDGE_FILE1_PATH) do f
    for l in eachline(f)
        splitted = split(l, "\t")
        vertex1 = parse(Int64, splitted[1]) + 1
        vertex2 = parse(Int64, splitted[2]) + 1

        append!(I, [vertex1])
        append!(J, [vertex2])
        append!(V, [1])

        append!(I, [vertex2])
        append!(J, [vertex1])
        append!(V, [1])
    end
end

S1 = sparse(I, J, V, vertex_counter, vertex_counter)

I = Int64[]
J = Int64[]
V = Int64[]
open(EDGE_FILE2_PATH) do f
    for l in eachline(f)
        splitted = split(l, "\t")
        vertex1 = parse(Int64, splitted[1]) + 1
        vertex2 = parse(Int64, splitted[2]) + 1

        append!(I, [vertex1])
        append!(J, [vertex2])
        append!(V, [1])

        append!(I, [vertex2])
        append!(J, [vertex1])
        append!(V, [1])
    end
end
S2 = sparse(I, J, V, vertex_counter, vertex_counter)

############################ IMPLEMENT OPERATIONS ############################

# Square
println("S1 * S1")
M = S1 * S2
println(length(M))
