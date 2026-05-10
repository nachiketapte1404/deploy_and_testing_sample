import { Component, OnInit, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  tasks: string[] = [];
  private platformId = inject(PLATFORM_ID);
  constructor(private http: HttpClient) { }

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.http.get<string[]>('http://localhost:8080/api/tasks').subscribe({
        next: (data) => this.tasks = data,
        error: (err) => console.error('Could not fetch tasks', err)
      });
    }
  }
}