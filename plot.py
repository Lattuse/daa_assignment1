import pandas as pd
import matplotlib.pyplot as plt


df = pd.read_csv("results/results.csv")

print("👉 Columns:", df.columns.tolist())
print(df.head())

df_mean = df.groupby(["algo", "n"]).mean().reset_index()

# Time vs n
plt.figure(figsize=(8, 5))
for algo in df_mean['algo'].unique():
    sub = df_mean[df_mean['algo'] == algo]
    plt.plot(sub['n'], sub['elapsed_ns'] / 1e6, marker='o', label=algo)  # переводим в миллисекунды
plt.xlabel("n (array size)")
plt.ylabel("time (ms)")
plt.title("Time vs n")
plt.legend()
plt.grid(True)
plt.savefig("results/time_vs_n.png", dpi=150)

# Depth vs n
plt.figure(figsize=(8, 5))
for algo in df_mean['algo'].unique():
    sub = df_mean[df_mean['algo'] == algo]
    plt.plot(sub['n'], sub['max_depth'], marker='s', label=algo)
plt.xlabel("n (array size)")
plt.ylabel("recursion depth")
plt.title("Depth vs n")
plt.legend()
plt.grid(True)
plt.savefig("results/depth_vs_n.png", dpi=150)

# Comparisons vs n
plt.figure(figsize=(8, 5))
for algo in df_mean['algo'].unique():
    sub = df_mean[df_mean['algo'] == algo]
    plt.plot(sub['n'], sub['comparisons'], marker='^', label=algo)
plt.xlabel("n (array size)")
plt.ylabel("comparisons")
plt.title("Comparisons vs n")
plt.legend()
plt.grid(True)
plt.savefig("results/comparisons_vs_n.png", dpi=150)

print("✅ Графики сохранены: results/time_vs_n.png, depth_vs_n.png, comparisons_vs_n.png")

