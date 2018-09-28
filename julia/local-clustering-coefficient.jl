###############################################################################
# Julia script to read a graph as vertex/edge files and transform it
# to a sparse adjacency matrix.
# * Parameter1: Path to edge file
# Example: julia graph-to-parse.jl test-vertices.csv test-edges.csv
###############################################################################

NODE_FILE_PATH = "test-nodes.csv"
EDGE_FILE_PATH = "test-edges.csv"

# Count the number of vertices
vertex_counter = 0
open(NODE_FILE_PATH) do f
    for l in eachline(f)
        global vertex_counter += 1
    end
end

# Triplets to store edges
I = Int64[]
J = Int64[]
V = Int64[]

# Loop through the edges, and fill the triplets with (i, j, 1) format.
# Increase the nodes id by 1 to match Julias array indices.
open(EDGE_FILE_PATH) do f
    for l in eachline(f)
        splitted = split(l, "\t")
        vertex1 = parse(Int64, splitted[1]) + 1
        vertex2 = parse(Int64, splitted[3]) + 1
        append!(I, [vertex1])
        append!(J, [vertex2])
        append!(V, [1])
    end
end

using SparseArrays

S = sparse(I, J, V, vertex_counter, vertex_counter)

println(S)
println()
############################ IMPLEMENT OPERATIONS ############################

# Square
println("S * S")
println(S * S)
